import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';

type EntityResponseType = HttpResponse<IEdadTipoPrueba>;
type EntityArrayResponseType = HttpResponse<IEdadTipoPrueba[]>;

@Injectable({ providedIn: 'root' })
export class EdadTipoPruebaService {
  public resourceUrl = SERVER_API_URL + 'api/edad-tipo-pruebas';

  constructor(protected http: HttpClient) {}

  create(edadTipoPrueba: IEdadTipoPrueba): Observable<EntityResponseType> {
    return this.http.post<IEdadTipoPrueba>(this.resourceUrl, edadTipoPrueba, { observe: 'response' });
  }

  update(edadTipoPrueba: IEdadTipoPrueba): Observable<EntityResponseType> {
    return this.http.put<IEdadTipoPrueba>(this.resourceUrl, edadTipoPrueba, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEdadTipoPrueba>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEdadTipoPrueba[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
