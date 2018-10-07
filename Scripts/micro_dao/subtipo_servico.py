from micro_dao.objeto_modelo import ObjetoModelo

class SubtipoServico(ObjetoModelo):

    _id: int
    _nome: str
    _fk_tipo_servico: int

    def __init__(self, nome_tipo: str, fk_tipo_servico: int):
        self._id = 1
        self.set_nome(nome_tipo)
        self.set_fk_tipo_servico(fk_tipo_servico)

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

    def get_fk_tipo_servico(self) -> int:
        return self._fk_tipo_servico

    def set_fk_tipo_servico(self, fk_tipo_servico: int):
        self._fk_tipo_servico = fk_tipo_servico
        return self