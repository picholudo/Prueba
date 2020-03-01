import { IEvaluacion } from 'app/shared/model/evaluacion.model';
import { Sexo } from 'app/shared/model/enumerations/sexo.model';
import { Nivelestudio } from 'app/shared/model/enumerations/nivelestudio.model';

export interface IPaciente {
  id?: number;
  sexo?: Sexo;
  profesion?: string;
  estudios?: Nivelestudio;
  edad?: number;
  evaluacions?: IEvaluacion[];
}

export class Paciente implements IPaciente {
  constructor(
    public id?: number,
    public sexo?: Sexo,
    public profesion?: string,
    public estudios?: Nivelestudio,
    public edad?: number,
    public evaluacions?: IEvaluacion[]
  ) {}
}
