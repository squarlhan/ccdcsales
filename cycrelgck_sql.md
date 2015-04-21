# Introduction #

储运处和工厂库关系对应表


# Details #

USE [test](test.md)
GO
/ 对象:  Table [dbo](dbo.md).[cycrelgck](cycrelgck.md)    脚本日期: 05/25/2009 15:38:14 /
SET ANSI\_NULLS ON
GO
SET QUOTED\_IDENTIFIER ON
GO
CREATE TABLE [dbo](dbo.md).[cycrelgck](cycrelgck.md)(
> [id](id.md) [int](int.md) IDENTITY(1,1) NOT NULL,
> [cyc](cyc.md) [int](int.md) NOT NULL,
> [gck](gck.md) [int](int.md) NOT NULL,
> CONSTRAINT [PK\_cycrelgck](PK_cycrelgck.md) PRIMARY KEY CLUSTERED
(
> [id](id.md) ASC
)WITH (PAD\_INDEX  = OFF, STATISTICS\_NORECOMPUTE  = OFF, IGNORE\_DUP\_KEY = OFF, ALLOW\_ROW\_LOCKS  = ON, ALLOW\_PAGE\_LOCKS  = ON) ON [PRIMARY](PRIMARY.md)
) ON [PRIMARY](PRIMARY.md)

GO
ALTER TABLE [dbo](dbo.md).[cycrelgck](cycrelgck.md)  WITH CHECK ADD  CONSTRAINT [FK\_cycrelgck\_Canku](FK_cycrelgck_Canku.md) FOREIGN KEY([cyc](cyc.md))
REFERENCES [dbo](dbo.md).[Canku](Canku.md) ([ID](ID.md))
GO
ALTER TABLE [dbo](dbo.md).[cycrelgck](cycrelgck.md) CHECK CONSTRAINT [FK\_cycrelgck\_Canku](FK_cycrelgck_Canku.md)
GO
ALTER TABLE [dbo](dbo.md).[cycrelgck](cycrelgck.md)  WITH CHECK ADD  CONSTRAINT [FK\_cycrelgck\_Canku1](FK_cycrelgck_Canku1.md) FOREIGN KEY([gck](gck.md))
REFERENCES [dbo](dbo.md).[Canku](Canku.md) ([ID](ID.md))
GO
ALTER TABLE [dbo](dbo.md).[cycrelgck](cycrelgck.md) CHECK CONSTRAINT [FK\_cycrelgck\_Canku1](FK_cycrelgck_Canku1.md)