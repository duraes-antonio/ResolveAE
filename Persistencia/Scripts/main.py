# encoding: utf-8
import argparse

from database import Database

args = argparse.ArgumentParser(description="Gerar tabelas já preenchidas.")

args.add_argument("-p", dest="path", type=str, required=True,
                  help="Path da pasta de saída dos arquivos SQL gerados.")
args.add_argument("-f", dest="fill", type=int, required=True,
                  help="Preencher tabelas e gerar SQL insert (0 p/ não, 1 p/ sim).")
args.add_argument("-n", dest="user", type=int,
                  help="Quantidade de usuários a ser inseridos.")
args.add_argument("-s", dest="single", type=int,
                  help="Arquivo SQL único (0 p/ não, 1 p/ sim).")
args.add_argument("-d", dest="divide", type=int,
                  help="Dividir arquivos por bloco de SQL (0 p/ não, 1 p/ sim).")
args.add_argument("-l", dest="len", type=int,
                  help="Quantidade de inserções por bloco de SQL (0 p/ não, 1 p/ sim).")

def main():


	db = Database(qtd_user=args.parse_args().user, database="resolve_ae")
	db.builder_all(False, args.parse_args().fill > 0)

	if (args.parse_args().fill > 0):
		single = args.parse_args().single if args.parse_args().single != None else False
		divide = args.parse_args().divide if args.parse_args().divide != None else False
		len = args.parse_args().len if args.parse_args().len != None else 500000

		db.to_arq_all_sql(single, divide, len, args.parse_args().path)

	else:
		db.to_arq_all_creates(args.parse_args().path)

	return 0


main()
