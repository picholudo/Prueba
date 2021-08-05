export interface IResultadoPrueba {
  id?: number;
  pd?: number;
  pz?: number;
  pruebaId?: number;
  informeId?: number;
}

export class ResultadoPrueba implements IResultadoPrueba {
  constructor(public id?: number, public pd?: number, public pz?: number, public pruebaId?: number, public informeId?: number) {}
}
