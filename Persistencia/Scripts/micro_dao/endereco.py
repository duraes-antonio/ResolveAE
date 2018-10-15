from micro_dao.objeto_modelo import ObjetoModelo

class Endereco(ObjetoModelo):

    _id: int
    _cep: int
    _fk_bairro: int
    _fk_usuario: int

    def __init__(self, cep: int, fk_bairro: int, fk_usuario: int):
        self._id = 1
        self.set_cep(cep)
        self.set_fk_bairro(fk_bairro)
        self.set_fk_usuario(fk_usuario)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_cep(self) -> int:
        return self._cep

    def set_cep(self, cep: int):
        self._cep = cep
        return self

    def get_fk_bairro(self) -> int:
        return self._fk_bairro

    def set_fk_bairro(self, fk_bairro: int):
        self._fk_bairro = fk_bairro
        return self

    def get_fk_usuario(self) -> int:
        return self._fk_usuario

    def set_fk_usuario(self, fk_usuario: int):
        self._fk_usuario = fk_usuario
        return self