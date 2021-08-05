export interface IEstadisticaMMSE {
  id?: number;
  media?: number;
  desviacionTipica?: number;
  codigoEstudioId?: number;
  edadTipoPruebaId?: number;
}

export class EstadisticaMMSE implements IEstadisticaMMSE {
  constructor(
    public id?: number,
    public media?: number,
    public desviacionTipica?: number,
    public codigoEstudioId?: number,
    public edadTipoPruebaId?: number
  ) {}
}
