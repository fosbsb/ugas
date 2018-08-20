#!/bin/sh
#
# Script MicroService Instituto Depilar v1.2
#
# chkconfig: 2345 80 20
# VARIAVEIS DO SCRIPT
PATH_MS=/opt/microservice2
PATH_FONTE=/fontes/institutodepilar/microservico
PATH_FILE_LOG=/logs/institutodepilar/instituto.log

#ACESSANDO O PATH AONDE ENCONTRA-SE OS ARQUIVOS FONTES
cd $PATH_FONTE
#ATUALIZANDO OS ARQUIVOS 
git pull
#AGUARDANDO 5 SEGUNDOS
sleep 5
#EXECUTANDO O MAVEN PARA GERAÇÃO DO PACOTE
mvn clean install
#AGUARDANDO 5 SEGUNDOS
sleep 5
#BUSCANDO O ÚLTIMO ARQUIVO .JAR GERADO NA PASTA TARGET
#CASO A GERAÇÃO DO PACOTE FALHE, ELE PEGARA A ULTIMCA VERSAO GERADA.
PATH_FILE=`ls $PATH_FONTE/target/*.jar -t | head -1`
#COPIANDO O ARQUIVO .JAR GERADO PARA O LOCAL PADRAO
cp -f $PATH_FILE $PATH_MS/institutodepilar
#COPIANDO O ARQUIVO .CONF PARA O LOCAL PADRÃO 
cp -f $PATH_FONTE/institutodepilar.conf $PATH_MS/institutodepilar.conf
#PARANDO O SERVIÇO JA MAPEADO

#Esse arquivo é uma copia do jar que foi gerado anteriormente.
systemctl stop institutodepilar
#AGUARDANDO 5 SEGUNDOS
sleep 5
#INICIANDO O SERVICO NOVAMENTE
systemctl start institutodepilar
#MOSTRANDO O LOG DA EXECUCAO
tail -f $PATH_FILE_LOG
######################################