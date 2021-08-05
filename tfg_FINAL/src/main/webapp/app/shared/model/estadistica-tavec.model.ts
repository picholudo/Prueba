export interface IEstadisticaTAVEC {
  id?: number;
  media?: number;
  desviacionTipica?: number;
  pruebaId?: number;
  edadTipoPruebaId?: number;
}

export class EstadisticaTAVEC implements IEstadisticaTAVEC {
  constructor(
    public id?: number,
    public media?: number,
    public desviacionTipica?: number,
    public pruebaId?: number,
    public edadTipoPruebaId?: number
  ) {}
}
