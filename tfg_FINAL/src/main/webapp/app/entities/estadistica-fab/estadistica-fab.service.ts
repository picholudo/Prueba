import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadisticaFAB } from 'app/shared/model/estadistica-fab.model';

type EntityResponseType = HttpResponse<IEstadisticaFAB>;
type EntityArrayResponseType = HttpResponse<IEstadisticaFAB[]>;

@Injectable({ providedIn: 'root' })
export class EstadisticaFABService {
  public resourceUrl = SERVER_API_URL + 'api/estadistica-fabs';

  constructor(protected http: HttpClient) {}

  create(estadisticaFAB: IEstadisticaFAB): Observable<EntityResponseType> {
    return this.http.post<IEstadisticaFAB>(this.resourceUrl, estadisticaFAB, { observe: 'response' });
  }

  update(estadisticaFAB: IEstadisticaFAB): Observable<EntityResponseType> {
    return this.http.put<IEstadisticaFAB>(this.resourceUrl, estadisticaFAB, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadisticaFAB>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadisticaFAB[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
