export interface IEstadisticaSSNeuronorma {
  id?: number;
  pd?: number;
  scaledScore?: number;
  pruebaId?: number;
  edadTipoPruebaId?: number;
}

export class EstadisticaSSNeuronorma implements IEstadisticaSSNeuronorma {
  constructor(
    public id?: number,
    public pd?: number,
    public scaledScore?: number,
    public pruebaId?: number,
    public edadTipoPruebaId?: number
  ) {}
}
