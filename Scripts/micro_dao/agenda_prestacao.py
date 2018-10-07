from micro_dao.objeto_modelo import ObjetoModelo

class AgendaPrestacao(ObjetoModelo):

    _id: int
    _fk_usuario: int

    def __init__(self, fk_usuario: int):
        self.set_fk_usuario(fk_usuario)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_fk_usuario(self) -> int:
        return self._fk_usuario

    def set_fk_usuario(self, fk_usuario: int):
        self._fk_usuario = fk_usuario
        return self