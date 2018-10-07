# encoding: utf-8
import abc

class ObjetoModelo(abc.ABC):

    @abc.abstractclassmethod
    def get_id(self) -> int: pass

    @abc.abstractclassmethod
    def set_id(self, id: int): pass