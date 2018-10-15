from micro_dao.objeto_modelo import ObjetoModelo

class DiaSemana(ObjetoModelo):

    _id: int
    _nome: str

    def __init__(self, nome_dia: str):
        self.set_dia(nome_dia)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_dia(self) -> str:
        return self._nome

    def set_dia(self, nome_dia: str):
        self._nome = nome_dia
        return self