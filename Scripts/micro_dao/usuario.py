from micro_dao.object_dao import ObjectDAO

class Usuario(ObjectDAO):

    def __init__(self, nome: str, email: str, senha: str, sobre: str, fk_informacoes: int):
        self._dic = {"id": -1, "nome": nome, "email": email, "senha": senha,
                     "sobre": sobre}

    def get_id(self) -> int:
        return self._dic["id"]

    def set_id(self, id):
        self._dic["id"] = id

    def get_nome(self) -> str:
        return self._dic["nome"]

    def set_nome(self, nome):
        self._dic["nome"] = nome

    def get_email(self) -> str:
        return self._dic["email"]

    def set_email(self, email):
        self._dic["email"] = email

    def get_senha(self) -> str:
        return self._dic["senha"]

    def set_senha(self, senha):
        self._dic["senha"] = senha

    def get_sobre(self) -> str:
        return self._dic["sobre"]

    def set_sobre(self, sobre):
        self._dic["sobre"] = sobre

    def get_dic(self) -> dict:
        return self._dic