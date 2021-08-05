import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IResultadoPrueba } from 'app/shared/model/resultado-prueba.model';

type EntityResponseType = HttpResponse<IResultadoPrueba>;
type EntityArrayResponseType = HttpResponse<IResultadoPrueba[]>;

@Injectable({ providedIn: 'root' })
export class ResultadoPruebaService {
  public resourceUrl = SERVER_API_URL + 'api/resultado-pruebas';

  constructor(protected http: HttpClient) {}

  create(resultadoPrueba: IResultadoPrueba): Observable<EntityResponseType> {
    return this.http.post<IResultadoPrueba>(this.resourceUrl, resultadoPrueba, { observe: 'response' });
  }

  update(resultadoPrueba: IResultadoPrueba): Observable<EntityResponseType> {
    return this.http.put<IResultadoPrueba>(this.resourceUrl, resultadoPrueba, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IResultadoPrueba>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResultadoPrueba[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
