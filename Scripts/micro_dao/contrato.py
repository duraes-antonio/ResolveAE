from datetime import datetime

from micro_dao.objeto_modelo import ObjetoModelo


class Contrato(ObjetoModelo):
	_id: int
	_data_inicio: datetime
	_data_fim: datetime
	_data_ult_modif: datetime
	_descricao: str
	_horas_contratadas: int
	_fk_usuario: int

	def __init__(self, data_inicio: datetime, data_fim: datetime, data_ult_modif: datetime,
	             descricao: str, horas_contratadas: int, fk_usuario: int):
		self.set_data_inicio(data_inicio)
		self.set_data_fim(data_fim)
		self.set_data_ultima_modif(data_ult_modif)
		self.set_descricao(descricao)
		self.set_horas_contratadas(horas_contratadas)
		self.set_fk_usuario(fk_usuario)

	def get_id(self) -> int:
		return self._id

	def set_id(self, id: int):
		self._id = id
		return self

	def set_data_inicio(self, data_contrato: datetime):
		self._data_inicio = data_contrato

	def get_data_inicio(self) -> datetime:
		return self._data_inicio

	def set_data_fim(self, data_fim_prest: datetime):
		self._data_fim = data_fim_prest

	def get_data_fim(self) -> datetime:
		return self._data_fim

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

	def set_horas_contratadas(self, horas_contratadas: int):
		self._horas_contratadas = horas_contratadas
		return self

	def get_horas_contratadas(self) -> int:
		return self._horas_contratadas