from micro_dao.objeto_modelo import ObjetoModelo

class Cidade(ObjetoModelo):


    _id: int
    _nome: str
    _fk_estado: int

    def __init__(self, nome_cidade: str, fk_estado: int):
        self.set_nome(nome_cidade)
        self.set_fk_estado(fk_estado)

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

    def get_fk_estado(self) -> int:
        return self._fk_estado

    def set_fk_estado(self, fk_estado: int):
        self._fk_estado = fk_estado
        return self
