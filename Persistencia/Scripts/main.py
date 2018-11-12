# encoding: utf-8
import argparse

from database import Database

args = argparse.ArgumentParser(description="Gerar tabelas já preenchidas.")
args.add_argument("-n", dest="user", type=int, required=True,
                  help="Quantidade de usuários a ser inseridos.")
args.add_argument("-p", dest="path", type=str, required=True,
                  help="Path de saída dos arquivos SQL gerados.")
args.add_argument("-f", dest="fill", type=int, required=True,
                  help="Preencher tabelas e gerar SQL insert (0 p/ não, 1 p/ sim).")
args.add_argument("-s", dest="single", type=int, required=True,
                  help="Arquivo SQL único (0 p/ não, 1 p/ sim).")

def main():
	db = Database(qtd_user=args.parse_args().user, database="resolve_ae")

	if (args.parse_args().user > 0):

		preencher = False
		arq_unico = False

		if args.parse_args().fill > 0:
			preencher = True

		if args.parse_args().single > 0:
			arq_unico = True

		db.builder_all(False, preencher)
		db.to_arq_all_sql(arq_unico, args.parse_args().path)

	return 0


main()
