# encoding: utf-8
import argparse

from database import Database

args = argparse.ArgumentParser(description="Gerar tabelas já preenchidas.")
args.add_argument("-n", dest="user", type=int, required=True,
                  help="Quantidade de usuários a ser inseridos.")

def main():
	db = Database(qtd_user=args.parse_args().user, database="resolve_ae")

	if (args.parse_args().user > 0):
		db.builder_all(False)
		db.to_arq_all_sql(args.parse_args().path)

	return 0


main()
