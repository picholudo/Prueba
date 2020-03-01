import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PuntosCorteService } from 'app/entities/puntos-corte/puntos-corte.service';
import { IPuntosCorte, PuntosCorte } from 'app/shared/model/puntos-corte.model';

describe('Service Tests', () => {
  describe('PuntosCorte Service', () => {
    let injector: TestBed;
    let service: PuntosCorteService;
    let httpMock: HttpTestingController;
    let elemDefault: IPuntosCorte;
    let expectedResult: IPuntosCorte | IPuntosCorte[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PuntosCorteService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PuntosCorte(0, 'AAAAAAA', currentDate, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            limite: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PuntosCorte', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            limite: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            limite: currentDate
          },
          returnedFromService
        );
        service
          .create(new PuntosCorte())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PuntosCorte', () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            limite: currentDate.format(DATE_TIME_FORMAT),
            superarlo: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            limite: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PuntosCorte', () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            limite: currentDate.format(DATE_TIME_FORMAT),
            superarlo: true
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            limite: currentDate
          },
          returnedFromService
        );
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a PuntosCorte', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
