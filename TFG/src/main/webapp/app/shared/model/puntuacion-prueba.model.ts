export interface IPuntuacionPrueba {
  id?: number;
  valor?: number;
  zscoreNombre?: string;
  zscoreId?: number;
  pacienteId?: number;
}

export class PuntuacionPrueba implements IPuntuacionPrueba {
  constructor(
    public id?: number,
    public valor?: number,
    public zscoreNombre?: string,
    public zscoreId?: number,
    public pacienteId?: number
  ) {}
}
