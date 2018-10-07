from micro_dao.objeto_modelo import ObjetoModelo

class TipoServico(ObjetoModelo):

    _id: int
    _nome: str

    def __init__(self, nome_tipo: str):
        self._id = 1
        self.set_nome(nome_tipo)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_nome(self) -> str:
        return self._nome

    def set_nome(self, nome_tipo: str):
        self._nome = nome_tipo
        return self