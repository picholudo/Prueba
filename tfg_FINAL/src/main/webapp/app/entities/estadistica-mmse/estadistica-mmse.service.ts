import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadisticaMMSE } from 'app/shared/model/estadistica-mmse.model';

type EntityResponseType = HttpResponse<IEstadisticaMMSE>;
type EntityArrayResponseType = HttpResponse<IEstadisticaMMSE[]>;

@Injectable({ providedIn: 'root' })
export class EstadisticaMMSEService {
  public resourceUrl = SERVER_API_URL + 'api/estadistica-mmses';

  constructor(protected http: HttpClient) {}

  create(estadisticaMMSE: IEstadisticaMMSE): Observable<EntityResponseType> {
    return this.http.post<IEstadisticaMMSE>(this.resourceUrl, estadisticaMMSE, { observe: 'response' });
  }

  update(estadisticaMMSE: IEstadisticaMMSE): Observable<EntityResponseType> {
    return this.http.put<IEstadisticaMMSE>(this.resourceUrl, estadisticaMMSE, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadisticaMMSE>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadisticaMMSE[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
