# encoding: utf-8
from typing import List, Optional

from micro_dao.tabela_model import Tabela
from micro_dao.tipo_info_profissional import TipoInfoProfissional


class TabelaTipoInfoProfissional(Tabela):

    _tipos = ["DOMÍNIO TECNOLÓGICO", "CURSO TÉCNICO", "GRADUAÇÃO", "PÓS-GRADUAÇÃO",
              "TRABALHO", "OUTRO CURSOS"]

    def __init__(self):
        super().__init__("tipo_info_profissional", TipoInfoProfissional.get_id)
        self.add_ID(TipoInfoProfissional.get_id)
        self.add_VARCHAR(TipoInfoProfissional.get_nome, "nome", 20, True)
        self.__inserted__ = False
        self.__preencher__()

    def __preencher__(self):
        [self.insert(TipoInfoProfissional(tipo)) for tipo in self._tipos]
        self.__inserted__ = True
        return self

    def get_all(self) -> List[TipoInfoProfissional]:
        return super().get_all()

    def get_by_nome(self, nome: str) -> TipoInfoProfissional:
        return super().buscar(TipoInfoProfissional.get_nome, nome)

    def get_by_indice(self, i: int) -> Optional[TipoInfoProfissional]:
        return super().get_by_indice(i)

    def str_dominio_tecnologico(self) -> str:
        return self._tipos[0]

    def str_curso_tecnico(self) -> str:
        return self._tipos[1]

    def str_graduacao(self) -> str:
        return self._tipos[2]

    def str_pos_graduacao(self) -> str:
        return self._tipos[3]

    def str_trabalho(self) -> str:
        return self._tipos[4]

    def str_outros_cursos(self) -> str:
        return self._tipos[5]