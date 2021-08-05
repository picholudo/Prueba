import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPaciente } from 'app/shared/model/paciente.model';

type EntityResponseType = HttpResponse<IPaciente>;
type EntityArrayResponseType = HttpResponse<IPaciente[]>;

@Injectable({ providedIn: 'root' })
export class PacienteService {
  public resourceUrl = SERVER_API_URL + 'api/pacientes';

  constructor(protected http: HttpClient) {}

  create(paciente: IPaciente): Observable<EntityResponseType> {
    return this.http.post<IPaciente>(this.resourceUrl, paciente, { observe: 'response' });
  }

  update(paciente: IPaciente): Observable<EntityResponseType> {
    return this.http.put<IPaciente>(this.resourceUrl, paciente, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPaciente>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPaciente[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
