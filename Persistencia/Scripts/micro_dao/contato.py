from micro_dao.objeto_modelo import ObjetoModelo

class Contato(ObjetoModelo):

    _id: int
    _descricao: str
    _fk_tipo: int
    _fk_usuario: int

    def __init__(self, fk_tipo: int, conteudo: str, fk_usuario: int):
        self.set_fk_tipo_cont(fk_tipo)
        self.set_fk_usuario(fk_usuario)
        self.set_descricao(conteudo)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_descricao(self) -> str:
        return self._descricao

    def set_descricao(self, descricao: str):
        self._descricao = descricao
        return self

    def get_fk_tipo_cont(self) -> int:
        return self._fk_tipo

    def set_fk_tipo_cont(self, fk_tipo: int):
        self._fk_tipo = fk_tipo
        return self

    def get_fk_usuario(self) -> int:
        return self._fk_usuario

    def set_fk_usuario(self, fk_usuario: int):
        self._fk_usuario = fk_usuario
        return self
