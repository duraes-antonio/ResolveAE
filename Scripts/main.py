# encoding: utf-8
from micro_dao.estado_tabela import TabelaEstado
from micro_dao.cidade_tabela import TabelaCidade
from micro_dao.usuario_tabela import TabelaUsuario
# class Temp():
#
#     dic = {}
#
#     def convert_to_dic(self, linha: str):
#
#         l_split = linha.split("','")
#         print(l_split)
#
#         if l_split[2] not in self.dic:
#             self.dic[l_split[2]] = {"bairro": [l_split[1]],
#                                     "estado": l_split[3].split("'")[0]}
#
#         else:
#             self.dic[l_split[2]]["bairro"].append(l_split[1])
#
#         return 0
#
#     def convert_to_estado_cidade(self, path: str):
#
#         json_c_b_e = None
#
#         with open(path, 'r') as arq:
#             json_c_b_e = json.load(arq)
#
#
#         for cidade in json_c_b_e:
#
#             if not json_c_b_e[cidade]["estado"] in self.dic:
#                 self.dic[json_c_b_e[cidade]["estado"]] = {cidade: sorted(json_c_b_e[cidade]["bairro"])}
#
#             else:
#
#                 if cidade not in self.dic[json_c_b_e[cidade]["estado"]]:
#                     self.dic[json_c_b_e[cidade]["estado"]][cidade] = sorted(json_c_b_e[cidade]["bairro"])
#
#         return self.dic
#
#     def convert_to_estado_cidade2(self, path: str):
#
#         json_c_b_e = None
#
#         with open(path, 'r') as arq:
#             json_c_b_e = json.load(arq)
#
#
#         for cidade in json_c_b_e:
#
#             if not json_c_b_e[cidade]["estado"] in self.dic:
#                 self.dic[json_c_b_e[cidade]["estado"]] = [cidade]
#
#             else:
#
#                 if cidade not in self.dic[json_c_b_e[cidade]["estado"]]:
#                     self.dic[json_c_b_e[cidade]["estado"]].append(cidade)
#
#         return self.dic
#
# def get_arquivo(caminho):
#
#     with open(caminho, 'r') as arq:
#         return arq.read()
#
# def gerar_arquivo(texto, nome_arq):
#
#     with open(nome_arq, "w") as arq:
#         arq.write(texto)
#
#     return texto
#
# def limpar_arquivo(path: str):
#
#     temp = Temp()
#     with open(path, 'r') as arq:
#         linhas = [linha.strip() for linha in arq.readlines()]
#
#     [temp.convert_to_dic(linha) for linha in linhas]
#
#     return json.dumps(temp.dic)
#
# def get_lista_restritos(path):
#
#     path2 = "/home/x/Área de Trabalho/restritos.txt"
#     linhas = []
#     restritos = []
#
#     with open(path2, 'r') as arq:
#         restritos = [linha.strip() for linha in arq.readlines()]
#
#     with open(path, 'r') as arq:
#         linhas = [linha for linha in arq.readlines()]
#
#     for i in range(len(linhas)):
#
#         if not linhas[i].split(',')[0][2:-1] in restritos:
#             linhas[i] = linhas[i].replace(" - ", "','")
#
#         else:
#             x = linhas[i].split(" - ")
#             print(x)
#             linhas[i] = "{0}','{1}".format(" - ".join(x[0:-1]), x[-1])
#             print(linhas[i])
#
#     gerar_arquivo("".join(linhas), "/home/x/Área de Trabalho/novo.txt")
#
# def get_cidade_nome(path_novo):
#
#     expressao = re.compile(r"'([\w\s`\.\(\)-_]*)'[,\s]+(\d{1,2})\)")
#     with open(path_novo, 'r') as arq:
#         x = expressao.findall(arq.read())
#         dic = {}
#         tab = Estado().get_tabela()
#
#         for tupla in x:
#
#             if tab.get_BY_ID(int(tupla[1]))["sigla"] not in dic:
#                 dic[tab.get_BY_ID(int(tupla[1]))["sigla"]] = [tupla[0]]
#
#             else:
#                 dic[tab.get_BY_ID(int(tupla[1]))["sigla"]].append(tupla[0])
#
#         return dic
#
# def get_estado_cidade_bairros(path_novo):
#
#     expressao = re.compile(r",'([\w\s\.`-]+)','([\w\s\.`-]+)','([\w]{2})'")
#
#     with open(path_novo, 'r') as arq:
#         x = expressao.findall(arq.read())
#         dic = {}
#         tab = Estado().get_tabela()
#
#         for tupla in x:
#
#             if tupla[2] not in dic:
#                 dic[tupla[2]] = {tupla[1]: [tupla[0]]}
#
#             else:
#
#                 if not tupla[1] in dic[tupla[2]]:
#                     dic[tupla[2]][tupla[1]] = [tupla[0]]
#
#                 else:
#                     dic[tupla[2]][tupla[1]].append(tupla[0])
#
#         return dic
#
# def get_cidade_bairros_estado(path_novo):
#
#     expressao = re.compile(r",'([\w\s\.`-]+)','([\w\s\.`-]+)','([\w]{2})'")
#
#     with open(path_novo, 'r') as arq:
#         x = expressao.findall(arq.read())
#         dic = {}
#         tab = Estado().get_tabela()
#
#         for tupla in x:
#
#             if tupla[2] not in dic:
#                 dic[tupla[2]] = {tupla[1]: [tupla[0]]}
#
#             else:
#
#                 if not tupla[1] in dic[tupla[2]]:
#                     dic[tupla[2]][tupla[1]] = [tupla[0]]
#
#                 else:
#                     dic[tupla[2]][tupla[1]].append(tupla[0])
#
#         return dic
#
# def get_estado_cidade_bairro(path_novo):
#
#     from operator import itemgetter
#
#     expressao = re.compile(r",'([\w\s\.`-]+)','([\w\s\.`-]+)','([\w]{2})'")
#
#     with open(path_novo, 'r') as arq:
#         x = expressao.findall(arq.read())
#         lista = [{"estado": tupla[2], "cidade": tupla[1], "bairro": tupla[0]} for tupla in x]
#         return sorted(lista, key=itemgetter("estado", "cidade", "bairro"))

def main():

    path = "/home/x/Área de Trabalho/novo.txt"
    path2 = "/home/x/Área de Trabalho/cidade_bairros_estado.json"

    print(TabelaUsuario().get_SQL_INSERT())
    return 0


main()