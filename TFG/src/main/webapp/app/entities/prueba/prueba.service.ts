import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPrueba } from 'app/shared/model/prueba.model';

type EntityResponseType = HttpResponse<IPrueba>;
type EntityArrayResponseType = HttpResponse<IPrueba[]>;

@Injectable({ providedIn: 'root' })
export class PruebaService {
  public resourceUrl = SERVER_API_URL + 'api/pruebas';

  constructor(protected http: HttpClient) {}

  create(prueba: IPrueba): Observable<EntityResponseType> {
    return this.http.post<IPrueba>(this.resourceUrl, prueba, { observe: 'response' });
  }

  update(prueba: IPrueba): Observable<EntityResponseType> {
    return this.http.put<IPrueba>(this.resourceUrl, prueba, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPrueba>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrueba[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
