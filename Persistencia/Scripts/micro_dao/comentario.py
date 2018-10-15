from micro_dao.objeto_modelo import ObjetoModelo

class Comentario(ObjetoModelo):

    _id: int
    _comentario: str
    _fk_avaliacao: int

    def __init__(self, comentario: str, fk_avaliacao: int):
        self.set_comentario(comentario)
        self.set_fk_avaliacao(fk_avaliacao)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_comentario(self) -> str:
        return self._comentario

    def set_comentario(self, comentario: str):
        self._comentario = comentario
        return self

    def set_fk_avaliacao(self, fk_avaliacao: int):
        self._fk_avaliacao = fk_avaliacao
        return self

    def get_fk_avaliacao(self) -> int:
        return self._fk_avaliacao