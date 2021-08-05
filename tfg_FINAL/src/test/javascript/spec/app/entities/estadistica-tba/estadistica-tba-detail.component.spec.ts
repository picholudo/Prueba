import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaTBADetailComponent } from 'app/entities/estadistica-tba/estadistica-tba-detail.component';
import { EstadisticaTBA } from 'app/shared/model/estadistica-tba.model';

describe('Component Tests', () => {
  describe('EstadisticaTBA Management Detail Component', () => {
    let comp: EstadisticaTBADetailComponent;
    let fixture: ComponentFixture<EstadisticaTBADetailComponent>;
    const route = ({ data: of({ estadisticaTBA: new EstadisticaTBA(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaTBADetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EstadisticaTBADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaTBADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadisticaTBA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadisticaTBA).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
