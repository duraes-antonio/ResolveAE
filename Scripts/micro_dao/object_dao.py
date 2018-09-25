# encoding: utf-8
import abc

class ObjectDAO(abc.ABC):

    @abc.abstractmethod
    def get_id(self) -> int: pass

    @abc.abstractmethod
    def set_id(self, id: int): pass

    @abc.abstractmethod
    def get_dic(self) -> dict: pass