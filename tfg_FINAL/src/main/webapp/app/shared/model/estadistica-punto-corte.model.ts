export interface IEstadisticaPuntoCorte {
  id?: number;
  puntoCorte?: number;
  pruebaId?: number;
}

export class EstadisticaPuntoCorte implements IEstadisticaPuntoCorte {
  constructor(public id?: number, public puntoCorte?: number, public pruebaId?: number) {}
}
