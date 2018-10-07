from micro_dao.objeto_modelo import ObjetoModelo

class Usuario(ObjetoModelo):

    _id: int
    _email: str
    _nome: str
    _senha: str
    _sobre: str
    prestador: bool

    def __init__(self, email: str, nome: str, senha: str, sobre: str,
                 prestador: bool = False):
        self.set_email(email)
        self.set_nome(nome)
        self.set_senha(senha)
        self.set_sobre(sobre)
        self.prestador = prestador

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def get_email(self) -> str:
        return self._email

    def set_email(self, email: str):
        self._email = email
        return self

    def get_nome(self) -> str:
        return self._nome

    def set_nome(self, nome: str):
        self._nome = nome
        return self

    def get_senha(self) -> str:
        return self._senha

    def set_senha(self, senha: str):
        self._senha = senha
        return self

    def get_sobre(self) -> str:
        return self._sobre

    def set_sobre(self, sobre: str):
        self._sobre = sobre
        return self