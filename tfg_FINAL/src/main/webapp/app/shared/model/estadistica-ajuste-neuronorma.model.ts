export interface IEstadisticaAjusteNeuronorma {
  id?: number;
  scaledScore?: number;
  ajusteEstudios?: number;
  pruebaId?: number;
  codigoEstudioId?: number;
}

export class EstadisticaAjusteNeuronorma implements IEstadisticaAjusteNeuronorma {
  constructor(
    public id?: number,
    public scaledScore?: number,
    public ajusteEstudios?: number,
    public pruebaId?: number,
    public codigoEstudioId?: number
  ) {}
}
