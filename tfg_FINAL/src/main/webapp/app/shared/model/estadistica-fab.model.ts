export interface IEstadisticaFAB {
  id?: number;
  media?: number;
  desviacionTipica?: number;
  codigoEstudioId?: number;
  edadTipoPruebaId?: number;
}

export class EstadisticaFAB implements IEstadisticaFAB {
  constructor(
    public id?: number,
    public media?: number,
    public desviacionTipica?: number,
    public codigoEstudioId?: number,
    public edadTipoPruebaId?: number
  ) {}
}
