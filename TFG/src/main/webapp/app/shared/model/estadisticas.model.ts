import { Nivelestudio } from 'app/shared/model/enumerations/nivelestudio.model';
import { Sexo } from 'app/shared/model/enumerations/sexo.model';

export interface IEstadisticas {
  id?: number;
  edad?: number;
  estudios?: Nivelestudio;
  sexo?: Sexo;
  prueba?: string;
  media?: number;
  desviacion?: number;
  zscoreNombre?: string;
  zscoreId?: number;
}

export class Estadisticas implements IEstadisticas {
  constructor(
    public id?: number,
    public edad?: number,
    public estudios?: Nivelestudio,
    public sexo?: Sexo,
    public prueba?: string,
    public media?: number,
    public desviacion?: number,
    public zscoreNombre?: string,
    public zscoreId?: number
  ) {}
}
