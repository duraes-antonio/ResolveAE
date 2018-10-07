# encoding: utf-8
from database import Database


def main():

    db = Database(qtd_user=1500000)
    db.builder_all()
    db.to_arq_sql_all()

    return 0

main()
