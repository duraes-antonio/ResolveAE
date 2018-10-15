from micro_dao.objeto_modelo import ObjetoModelo


class Bairro(ObjetoModelo):
    _id: int
    _nome: str
    _fk_cidade: int

    def __init__(self, nome_bairro: str, fk_cidade: int):
        self.set_nome(nome_bairro)
        self.set_fk_cidade(fk_cidade)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_nome(self) -> str:
        return self._nome

    def set_nome(self, nome_cidade: str):
        self._nome = nome_cidade
        return self

    def get_fk_cidade(self) -> int:
        return self._fk_cidade

    def set_fk_cidade(self, fk_cidade: int):
        self._fk_cidade = fk_cidade
        return self
