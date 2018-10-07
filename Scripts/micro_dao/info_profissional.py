from datetime import datetime

from micro_dao.objeto_modelo import ObjetoModelo

class InfoProfissional(ObjetoModelo):


    _id: int
    _descricao: str
    _data_inicio: datetime
    _data_fim: datetime
    _fk_usuario: int
    _fk_tipo_info_profissional: int

    def __init__(self, descricao: str, data_inicio: datetime, data_fim: datetime,
                 fk_usuario: int, fk_tipo_info_pro: int):
        self.set_descricao(descricao)
        self.set_data_inicio(data_inicio)
        self.set_data_fim(data_fim)
        self.set_fk_usuario(fk_usuario)
        self.set_fk_tipo_info_prof(fk_tipo_info_pro)

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

    def get_data_inicio(self) -> datetime:
        return self._data_inicio

    def set_data_inicio(self, data_inicio: datetime):
        self._data_inicio = data_inicio
        return self

    def get_data_fim(self) -> datetime:
        return self._data_fim

    def set_data_fim(self, data_fim: datetime):
        self._data_fim = data_fim
        return self

    def get_fk_usuario(self) -> int:
        return self._fk_endereco

    def set_fk_usuario(self, fk_endereco: int):
        self._fk_endereco = fk_endereco
        return self

    def get_fk_tipo_info_prof(self) -> int:
        return self._fk_tipo_info_profissional

    def set_fk_tipo_info_prof(self, fk_tipo_info_pro: int):
        self._fk_tipo_info_profissional = fk_tipo_info_pro
        return self