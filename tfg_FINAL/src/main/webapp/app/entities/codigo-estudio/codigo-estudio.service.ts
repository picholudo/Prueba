import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICodigoEstudio } from 'app/shared/model/codigo-estudio.model';

type EntityResponseType = HttpResponse<ICodigoEstudio>;
type EntityArrayResponseType = HttpResponse<ICodigoEstudio[]>;

@Injectable({ providedIn: 'root' })
export class CodigoEstudioService {
  public resourceUrl = SERVER_API_URL + 'api/codigo-estudios';

  constructor(protected http: HttpClient) {}

  create(codigoEstudio: ICodigoEstudio): Observable<EntityResponseType> {
    return this.http.post<ICodigoEstudio>(this.resourceUrl, codigoEstudio, { observe: 'response' });
  }

  update(codigoEstudio: ICodigoEstudio): Observable<EntityResponseType> {
    return this.http.put<ICodigoEstudio>(this.resourceUrl, codigoEstudio, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICodigoEstudio>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICodigoEstudio[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
