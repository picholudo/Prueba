import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEstadisticas } from 'app/shared/model/estadisticas.model';

type EntityResponseType = HttpResponse<IEstadisticas>;
type EntityArrayResponseType = HttpResponse<IEstadisticas[]>;

@Injectable({ providedIn: 'root' })
export class EstadisticasService {
  public resourceUrl = SERVER_API_URL + 'api/estadisticas';

  constructor(protected http: HttpClient) {}

  create(estadisticas: IEstadisticas): Observable<EntityResponseType> {
    return this.http.post<IEstadisticas>(this.resourceUrl, estadisticas, { observe: 'response' });
  }

  update(estadisticas: IEstadisticas): Observable<EntityResponseType> {
    return this.http.put<IEstadisticas>(this.resourceUrl, estadisticas, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEstadisticas>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEstadisticas[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
