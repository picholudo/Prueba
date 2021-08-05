import { IInforme } from 'app/shared/model/informe.model';
import { Sexo } from 'app/shared/model/enumerations/sexo.model';
import { NivelEstudios } from 'app/shared/model/enumerations/nivel-estudios.model';

export interface IPaciente {
  id?: number;
  nhc?: number;
  nombre?: string;
  sexo?: Sexo;
  profesion?: string;
  estudios?: NivelEstudios;
  edad?: number;
  informes?: IInforme[];
}

export class Paciente implements IPaciente {
  constructor(
    public id?: number,
    public nhc?: number,
    public nombre?: string,
    public sexo?: Sexo,
    public profesion?: string,
    public estudios?: NivelEstudios,
    public edad?: number,
    public informes?: IInforme[]
  ) {}
}
