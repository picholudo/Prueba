export interface IEstadisticaPzNeuronorma {
  id?: number;
  ajusteEstudios?: number;
  pz?: number;
}

export class EstadisticaPzNeuronorma implements IEstadisticaPzNeuronorma {
  constructor(public id?: number, public ajusteEstudios?: number, public pz?: number) {}
}
