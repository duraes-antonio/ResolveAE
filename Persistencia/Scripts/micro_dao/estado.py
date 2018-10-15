from micro_dao.objeto_modelo import ObjetoModelo

class Estado(ObjetoModelo):

    _id: int
    _nome: str
    _sigla: str

    def __init__(self, nome_estado: str, sigla: str):
        self._id = 1
        self.set_nome(nome_estado)
        self.set_sigla(sigla)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_nome(self) -> str:
        return self._nome

    def set_nome(self, nome_estado: str):
        self._nome = nome_estado
        return self

    def get_sigla(self) -> str:
        return self._sigla

    def set_sigla(self, sigla: str):
        self._sigla = sigla
        return self