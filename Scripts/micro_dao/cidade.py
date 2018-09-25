from micro_dao.object_dao import ObjectDAO

class Cidade(ObjectDAO):

    def __init__(self, nome_cidade: str, fk_estado: int):
        self._dic = {"id": -1, "nome": nome_cidade, "fk_estado": fk_estado}

    def get_id(self) -> int:
        return self._dic["id"]

    def set_id(self, id):
        self._dic["id"] = id

    def get_nome(self) -> str:
        return self._dic["nome"]

    def set_nome(self, nome_cidade):
        self._dic["nome"] = nome_cidade

    def get_sigla(self) -> str:
        return self._dic["sigla"]

    def set_sigla(self, sigla):
        self._dic["sigla"] = sigla

    def get_dic(self) -> dict:
        return self._dic