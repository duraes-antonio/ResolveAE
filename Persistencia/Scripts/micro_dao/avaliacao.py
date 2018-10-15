from micro_dao.objeto_modelo import ObjetoModelo

class Avaliacao(ObjetoModelo):

    _id: int
    _nota: int
    _fk_servico_prestacao: int
    _fk_usuario: int

    def __init__(self, nota: int, fk_servico_prestacao: int, fk_usuario: int):
        self.set_nota(nota)
        self.set_fk_servico_prestacao(fk_servico_prestacao)
        self.set_fk_usuario(fk_usuario)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_nota(self) -> int:
        return self._nota

    def set_nota(self, nota: int):
        self._nota = nota
        return self

    def set_fk_servico_prestacao(self, fk_serv_prest: int):
        self._fk_servico_prestacao = fk_serv_prest
        return self

    def get_fk_servico_prestacao(self) -> float:
        return self._fk_servico_prestacao

    def get_fk_usuario(self) -> int:
        return self._fk_usuario

    def set_fk_usuario(self, fk_usuario: int):
        self._fk_usuario = fk_usuario
        return self