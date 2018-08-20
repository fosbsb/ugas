# Script de Backup Banco de dados MySQL #
############################################################
# Confifgurações no Crontab                                #
############################################################
# Executar backup do banco a meia noite e ao meio dia
# Este horário está considerando GMT-2
# 00 02,14 * * * root /fontes/git/ugas/msugas/backup_mysql.sh
# Comprimir os Backups todo dia as 2 horas da manhã
# 01 4 * * * root /fontes/git/ugas/banco_de_dados/comprimir-backups-mysql.sh
############################################################

#Data
NOW=$(date +"%m-%d-%Y-%T")
echo "Iniciando Backup $NOW ..."
#User
USER="backup"
PASSWORD="WTinzWahiRxUElMr"
#File 
FILE="backup-ugas.$NOW.sql"

mysqldump ugas -hbd.ugas.com.br -u$USER -p$PASSWORD --routines >> /fontes/bd/$FILE

echo "Backup finalizado $FILE"
