export interface IEstadisticaTBA {
  id?: number;
  media?: number;
  desviacionTipica?: number;
  pruebaId?: number;
  codigoEstudioId?: number;
  edadTipoPruebaId?: number;
}

export class EstadisticaTBA implements IEstadisticaTBA {
  constructor(
    public id?: number,
    public media?: number,
    public desviacionTipica?: number,
    public pruebaId?: number,
    public codigoEstudioId?: number,
    public edadTipoPruebaId?: number
  ) {}
}
