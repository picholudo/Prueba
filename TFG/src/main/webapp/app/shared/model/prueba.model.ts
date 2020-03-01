export interface IPrueba {
  id?: number;
  nombre?: string;
}

export class Prueba implements IPrueba {
  constructor(public id?: number, public nombre?: string) {}
}
