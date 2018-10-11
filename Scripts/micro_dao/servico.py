from micro_dao.objeto_modelo import ObjetoModelo

class Servico(ObjetoModelo):

    _id: int
    _valor: float
    _titulo: str
    _fk_usuario: int
    _fk_tipo_servico: int
    _fk_subtipo_servico: int
    _fk_contrato: int

    def __init__(self, valor: float, titulo: str, fk_usuario: int,
                 fk_tipo_servico: int, fk_subtipo_servico: int, fk_contrato: int):
        self.set_valor(valor)
        self.set_titulo(titulo)
        self.set_fk_usuario(fk_usuario)
        self.set_fk_tipo_servico(fk_tipo_servico)
        self.set_fk_subtipo_servico(fk_subtipo_servico)
        self.set_fk_contrato(fk_contrato)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_valor(self) -> float:
        return self._valor

    def set_valor(self, valor: float):
        self._valor = valor
        return self

    def get_titulo(self) -> str:
        return self._titulo

    def set_titulo(self, titulo: str):
        self._titulo = titulo
        return self

    def set_fk_usuario(self, fk_usuario: int):
        self._fk_usuario = fk_usuario
        return self

    def get_fk_usuario(self) -> int:
        return self._fk_usuario

    def set_fk_tipo_servico(self, fk_tipo_servico: int):
        self._fk_tipo_servico = fk_tipo_servico
        return self

    def get_fk_tipo_servico(self) -> int:
        return self._fk_tipo_servico

    def set_fk_subtipo_servico(self, fk_subtipo_servico: int):
        self._fk_subtipo_servico= fk_subtipo_servico
        return self

    def get_fk_subtipo_servico(self) -> int:
        return self._fk_subtipo_servico

    def set_fk_contrato(self, fk_contrato: int):
        self._fk_contrato = fk_contrato
        return self

    def get_fk_contrato(self) -> int:
        return self._fk_contrato