# encoding: utf-8
from random import choice, randint
from typing import List

from micro_dao.avaliacao import Avaliacao
from micro_dao.servico_prestacao_tabela import TabelaServicoPrestacao
from micro_dao.tabela_model import Tabela
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaAvaliacao(Tabela):
    _num_min_aval = 1
    _num_max_aval = 3
    _nota_min = 1
    _nota_max = 5

    def __init__(self, tab_serv_prest: TabelaServicoPrestacao, tab_usu: TabelaUsuario):
        super().__init__("avaliacao", Avaliacao.get_id)
        self.add_ID(Avaliacao.get_id)
        self.add_VARCHAR(Avaliacao.get_comentario, "comentario", 500)
        self.add_FLOAT(Avaliacao.get_nota, "nota")
        self.add_FK(Avaliacao.get_fk_usuario, "fk_usuario", tab_usu)
        self.add_FK(Avaliacao.get_fk_servico_prestacao, "fk_servico_prestacao", tab_serv_prest)
        self.__inserted__ = False
        self.__preencher__(tab_serv_prest, tab_usu)

    def __preencher__(self, tab_serv_prest: TabelaServicoPrestacao, tab_usu: TabelaUsuario):

        nota_especial = [1, 2, 5]
        coments = {
            1: ["Péssimo {}.", "Não gostei do {}.", "Se não fosse o {}.",
                "O {} é bem ruim.", "É pelo {} que o Brasil não vai para frente."],
            2: ["Não gostei do {}.", "Poderia melhorar o {}.",
                "O que estraga é o {}.", "Se melhorar o {}, tem futuro."],
            3: ["Dentro do combinado.", "Tudo okay.", "Dentro do prazo",
                "Preço compátivel.", "Regular.", "Como esperado."],
            4: ["Ótimo.", "Gostei!", "Show.", "Melhor.", "Recomendo!",
                "Muito bom!"],
            5: ["Melhor {}.", "Não tem igual {}", "Não achei nenhum {} semelhante.",
                "Só pelo {}, já vale.", "O que mais compensa é o {}."]
        }
        atributos = ["atendimento", "prazo", "custo-benefício", "preço", "marketing"]

        # Para cada serviço, faça de 1 até 3 avaliações;
        for serv_prest in tab_serv_prest.get_all():

            for aval in range(randint(self._num_min_aval, self._num_max_aval)):
                nota = randint(self._nota_min, self._nota_max)

                if (nota in nota_especial):
                    comentario = choice(coments[nota]).format(choice(atributos))

                else: comentario = choice(coments[nota])

                self.insert(
                    Avaliacao(
                        randint(self._nota_min, self._nota_max),
                        comentario, serv_prest.get_id(),
                        serv_prest.get_fk_usuario()
                    )
                )

        self.__inserted__ = True
        return self

    def get_all(self) -> List[Avaliacao]:
        return super().get_all()