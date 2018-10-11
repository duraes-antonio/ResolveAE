from micro_dao.objeto_modelo import ObjetoModelo


class ServicoSubtipoServico(ObjetoModelo):

    _id: int
    _fk_servico: int
    _fk_subtipo_servico: int

    def __init__(self, fk_servico: int, fk_subtipo_servico: int):
        self.set_fk_servico(fk_servico)
        self.set_fk_subtipo_servico(fk_subtipo_servico)


    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def set_fk_servico(self, fk_servico: int):
        self._fk_servico = fk_servico
        return self

    def get_fk_servico(self) -> int:
        return self._fk_servico

    def set_fk_subtipo_servico(self, fk_subtipo_servico: int):
        self._fk_subtipo_servico = fk_subtipo_servico
        return self

    def get_fk_subtipo_servico(self) -> int:
        return self._fk_subtipo_servico