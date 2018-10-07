from datetime import datetime

from micro_dao.objeto_modelo import ObjetoModelo

class Contrato(ObjetoModelo):

    _id: int
    _data_contrato: datetime
    _data_inicio_prestacao: datetime
    _data_fim_prestacao: datetime
    _data_ult_modif: datetime
    _descricao: str
    _fk_usuario: int

    def __init__(self, data_contrato: datetime, data_ini_prest: datetime,
                 data_fim_prest: datetime, data_ult_modif: datetime,
                 descricao: str, fk_usuario: int):
        self.set_data_contrato(data_contrato)
        self.set_data_inicio_prestacao(data_ini_prest)
        self.set_data_fim_prestacao(data_fim_prest)
        self.set_data_ultima_modif(data_ult_modif)
        self.set_fk_usuario(fk_usuario)
        self.set_descricao(descricao)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def set_data_contrato(self, data_contrato: datetime):
        self._data_contrato = data_contrato

    def get_data_contrato(self) -> datetime:
        return self._data_contrato

    def set_data_inicio_prestacao(self, data_ini_prest: datetime):
        self._data_inicio_prestacao = data_ini_prest

    def get_data_inicio_prestacao(self) -> datetime:
        return self._data_inicio_prestacao

    def set_data_fim_prestacao(self, data_fim_prest: datetime):
        self._data_fim_prestacao = data_fim_prest

    def get_data_fim_prestacao(self) -> datetime:
        return self._data_fim_prestacao

    def set_data_ultima_modif(self, data_ult_modif: datetime):
        self._data_ult_modif = data_ult_modif

    def get_data_ultima_modif(self) -> datetime:
        return self._data_ult_modif

    def get_descricao(self) -> str:
        return self._descricao

    def set_descricao(self, descricao: str):
        self._descricao = descricao
        return self

    def get_fk_usuario(self) -> int:
        return self._fk_usuario

    def set_fk_usuario(self, fk_usuario: int):
        self._fk_usuario = fk_usuario
        return self
